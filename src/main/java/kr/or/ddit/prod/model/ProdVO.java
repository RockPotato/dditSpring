package kr.or.ddit.prod.model;

public class ProdVO {
	String prod_id; // 상품 id
	String prod_name; // 상품 이름
	String prod_buyer; // 상품 구매자
	String prod_cost; // 상품 가격
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public String getProd_buyer() {
		return prod_buyer;
	}
	public void setProd_buyer(String prod_buyer) {
		this.prod_buyer = prod_buyer;
	}
	public String getProd_cost() {
		return prod_cost;
	}
	public void setProd_cost(String prod_cost) {
		this.prod_cost = prod_cost;
	}
	@Override
	public String toString() {
		return "ProdVO [prod_id=" + prod_id + ", prod_name=" + prod_name
				+ ", prod_buyer=" + prod_buyer + ", prod_cost=" + prod_cost
				+ "]";
	}
}
