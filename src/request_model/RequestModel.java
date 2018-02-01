package request_model;

import java.util.ArrayList;

public class RequestModel implements java.io.Serializable {
	
	// GET or ADD or DELETE
	public String         method;
	
	// product or settlement or plan
	public String         scope;
	
	public Authentication authentication ;
	
	public Product product;
	public PlanContent PlanContent;
	public SettlementContent settlementContent;
	
	
	public RequestModel(
		String id,
		String password
	) {
		this.authentication = new Authentication(id, password);
	}
	
}

class Authentication {
	
	public String id;
	public String password;
	
	public Authentication(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
}


class Product {
	
	public Integer id;
	public String  name;
	public Integer price;
	public Integer stock;
	public String  updatedAt;
	
	public Product(dto.Product product) {
		id = product.getId();
		name = product.getName();
		price = product.getPrice();
		stock = product.getStock();
		updatedAt = product.getUpdatedAt();
	}
	
}

class SettlementContent {
	
	public Integer id;
	public ArrayList<Integer> productIds; 
	
	public SettlementContent(
			Integer id,
			ArrayList<Integer> productIds
	) {
		this.id         = id;
		this.productIds = productIds;
	}
}

class PlanContent {
	
	public Integer id;
	public double  coefficient;
	public Integer period;
	
	public PlanContent(dto.Plan plan) {
		this.id          = plan.getId();
		this.coefficient = plan.getCoefficient();
		this.period      = plan.getPeriod();
	}
	
}