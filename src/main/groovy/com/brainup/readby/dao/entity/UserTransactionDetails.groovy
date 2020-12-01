package com.brainup.readby.dao.entity

import com.brainup.readby.dto.MasCoursesDTO
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter

import javax.persistence.*
import java.sql.Timestamp

@Canonical
@EqualsAndHashCode
@Entity
@Table(name = 'user_transaction_details')
@Getter
@Setter
class UserTransactionDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'USER_TRANS_ID')
    @JsonProperty(value = 'USER_TRANS_ID')
    private Long userTransId

    @Column(name = 'ORDER_ID')
    @JsonProperty(value = 'ORDER_ID')
    private String orderId

    /*@Column(name = 'PAY_GWAY_TRANS_ID')
    @JsonProperty(value = 'PAY_GWAY_TRANS_ID')
    private String payGwayTransId*/

    @Column(name = 'CREATED_AT')
    @JsonProperty(value = 'CREATED_AT')
    @JsonIgnore
    private Timestamp createdAt = new Timestamp(new Date().getTime())

    @Column(name = 'CREATED_BY')
    @JsonProperty(value = 'CREATED_BY')
    @JsonIgnore
    private String createdBy = "readby"

    @Column(name = 'UPDATED_AT')
    @JsonProperty(value = 'UPDATED_AT')
    @JsonIgnore
    private Timestamp updatedAt = new Timestamp(new Date().getTime())

    @Column(name = 'UPDATED_BY')
    @JsonProperty(value = 'UPDATED_BY')
    @JsonIgnore
    private String updatedBy = "readby"

    @Column(name = 'USER_ID')
    @JsonProperty(value = 'USER_ID')
    private Long userid

    @Column(name = 'COURSE_ID')
    @JsonProperty(value = 'COURSE_ID')
    private Long courseId

    @Column(name = 'SUBJECT_ID')
    @JsonProperty(value = 'SUBJECT_ID')
    private Long subjectId

    @Column(name = 'PAYMENT_STATUS')
    @JsonProperty(value = 'PAYMENT_STATUS')
    private String paymentStatus

    @Column(name = 'TRANSACTION_AMOUNT')
    @JsonProperty(value = 'TRANSACTION_AMOUNT')
    private Double transactionAmount

    @Column(name = 'CHECKSUM_VAL')
    @JsonProperty(value = 'CHECKSUM_VAL')
    private String checksumVal

    @Column(name = 'BANKNAME')
    @JsonProperty(value = 'BANKNAME')
    private String bankName

    @Column(name = 'MID')
    @JsonProperty(value = 'MID')
    private String mid

    @Column(name = 'TXNID')
    @JsonProperty(value = 'TXNID')
    private String txnId

    @Column(name = 'RESPCODE')
    @JsonProperty(value = 'RESPCODE')
    private String respCode

    @Column(name = 'PAYMENTMODE')
    @JsonProperty(value = 'PAYMENTMODE')
    private String paymentMode

    @Column(name = 'BANKTXNID')
    @JsonProperty(value = 'BANKTXNID')
    private String bankTxnId

    @Column(name = 'GATEWAYNAME')
    @JsonProperty(value = 'GATEWAYNAME')
    private String gatewayName

    @Column(name = 'TXNDATE')
    @JsonProperty(value = 'TXNDATE')
    private Timestamp txnDate = new Timestamp(new Date().getTime())

    @Column(name = 'CURRENCY')
    @JsonProperty(value = 'CURRENCY')
    private String currency

    @Transient
    @JsonProperty(value = "SUBSCRIPTION_ID")
    private Long subscriptionId

}