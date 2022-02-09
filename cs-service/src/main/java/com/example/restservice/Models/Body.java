package com.example.restservice.Models;

import java.util.Arrays;
import java.util.Objects;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

//DynamoDB AWS integration

@DynamoDBTable(tableName = "BodyRequest")
public class Body {

	private Long id;
	private String status;
	private String detail;
    private String body;

	public Body() {
		// Default constructor is required by AWS DynamoDB SDK
	}

	public Body(Long id, String status, String detail, String body) {
        this.id = id;
		this.status = status;
        this.detail = detail;
        this.body = body;
	}

	@DynamoDBHashKey(attributeName = "Id")
	public Long getId() {
		return id;
	}

	@DynamoDBAttribute
	public String getStatus(){
        return status;
    }

	@DynamoDBAttribute
	public String getDetail(){
        return detail;
	}

    @DynamoDBAttribute
	public String getBody(){
        return body;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStatus(String status){
        this.status = status;
    }

	public void setDetail(String detail){
        this.detail = detail;
	}

	public void setBody(String body){
        this.body = body;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, status, detail, body);
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Body other = (Body) obj;
		return Arrays.deepEquals(new Object[]{id, status, detail, body},
				new Object[]{other.id, other.status, other.detail, other.body});
	}

}