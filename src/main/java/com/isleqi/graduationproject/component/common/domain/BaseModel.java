package com.isleqi.graduationproject.component.common.domain;

import java.io.Serializable;

public interface BaseModel<ID extends Serializable> extends Serializable{

	ID getId();
	
}
