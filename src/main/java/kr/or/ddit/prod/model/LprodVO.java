package kr.or.ddit.prod.model;

public class LprodVO {
	String lprod_id; // 상품 분류 id
	String lprod_gu; // 상품 분류 코드
	String lprod_nm; // 상품 분류 이름
	public String getLprod_id() {
		return lprod_id;
	}
	public void setLprod_id(String lprod_id) {
		this.lprod_id = lprod_id;
	}
	public String getLprod_gu() {
		return lprod_gu;
	}
	public void setLprod_gu(String lprod_gu) {
		this.lprod_gu = lprod_gu;
	}
	public String getLprod_nm() {
		return lprod_nm;
	}
	public void setLprod_nm(String lprod_nm) {
		this.lprod_nm = lprod_nm;
	}
	@Override
	public String toString() {
		return "LProdVO [lprod_id=" + lprod_id + ", lprod_gu=" + lprod_gu
				+ ", lprod_nm=" + lprod_nm + "]";
	}
	
}
