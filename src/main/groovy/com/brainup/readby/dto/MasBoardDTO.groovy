package com.brainup.readby.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Getter
import lombok.Setter

import javax.persistence.Column

@Getter
@Setter
class MasBoardDTO implements Serializable{

    @JsonProperty(value = 'BOARD_ID')
    private Long boardId

    @JsonProperty(value = 'BOARD_NAME')
    private String boardName

    @JsonProperty(value = 'BOARD_CODE')
    private String boardCode
}
