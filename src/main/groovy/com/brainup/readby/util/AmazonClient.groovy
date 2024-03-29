package com.brainup.readby.util

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.DeleteObjectRequest
import com.amazonaws.services.s3.model.PutObjectRequest
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

import javax.annotation.PostConstruct

@Service
@Slf4j
class AmazonClient {

    private AmazonS3 s3client

    @Value('${amazonProperties.endpointUrl}')
    private String endpointUrl

    @Value('${amazonProperties.bucketName}')
    private String bucketName

    @Value('${amazonProperties.accessKey}')
    private String accessKey

    @Value('${amazonProperties.secretKey}')
    private String secretKey



    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey)
        this.s3client = new AmazonS3Client(credentials)
    }

    public String uploadFile(MultipartFile multipartFile,String childFolder) {
        String fileUrl = ""
        try {
            File file = convertMultiPartToFile(multipartFile)
            String fileName = generateFileName(multipartFile)
            fileUrl = endpointUrl + "/" + childFolder + "/" + fileName
            uploadFileTos3bucket(fileName, file,childFolder)
            file.delete()
            return fileUrl
        } catch (Exception e) {
            e.printStackTrace()
            return null
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename())
        FileOutputStream fos = new FileOutputStream(convFile)
        fos.write(file.getBytes())
        fos.close()
        return convFile
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_")
    }

    private void uploadFileTos3bucket(String fileName, File file,String childFolder) {
        s3client.putObject(new PutObjectRequest(bucketName+ "/" + childFolder, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead))
    }

    public void deleteFileFromS3Bucket(String fileUrl,String childFolder) {
        if(fileUrl != null) {
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1)
            s3client.deleteObject(new DeleteObjectRequest(bucketName + "/" + childFolder, fileName))
            log.info "Successfully deleted"
        }else{
            log.info "fileurl is null"
        }
    }
}
