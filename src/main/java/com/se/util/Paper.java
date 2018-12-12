package com.se.util;

public class Paper {
	
	//page Number
	private int curPage; 		//외부에서 받아올려고
	
	//perPage : 숫자에 따라 달라지는 페이지수
	private int perPage;
	
	//perBlock
	private int perBlock; 
	
	//DAO rownum
	private int startRow; 		//자동계산
	private int lastRow;
	
	//search 검색
	private String kind;		//외부에서 받아올려고
	private String search;
	
	//pageing :처리해서 보내주는거
	private int startNum;
	private int lastNum;
	private int curBlock;
	private int totalBlock;
	
	public Paper() {
		this.perPage=10;
		this.perBlock=5;

	}
	
	
	
	public int getCurPage() {	//curPage=0으로 바꿔서 0/ 호출을 안해서 넘어오지 않는다.
		if(curPage==0) {
			curPage=1;					
		}
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPerPage() {
		if(perPage==0) {
			perPage=10;
		}
		return perPage;
	}
	public void setPerPage(int perPage) {
		if(perPage==0) {
			this.perPage=10;  //한페이지에다가 10페이지
		}else {
			this.perPage = perPage;
		}
		this.perPage = perPage;
	}
	public int getPerBlock() {
		if(perBlock==0) {
			perBlock=5;
		}
		return perBlock;
	}
	public void setPerBlock(int perBlock) {
		this.perBlock = perBlock;
		if(this.perBlock==0) {
			this.perBlock=5;
		}
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getLastRow() {
		return lastRow;
	}
	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}
	public String getKind() {
		if(kind==null||kind.equals("")) {
			kind="title";
		}
		return kind;
	}
	public void setKind(String kind) {		//kind도 null이라고 바꿔서 null
		this.kind = kind;
		if(this.kind == null|| this.kind.equals("")) {
			this.kind="title";
		}
	}
	public String getSearch() {
		if(search == null) {
			search="";
		}
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
		if(this.search == null) {
			this.search="";
		}
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getLastNum() {
		return lastNum;
	}
	public void setLastNum(int lastNum) {
		this.lastNum = lastNum;
	}
	public int getCurBlock() {
		return curBlock;
	}
	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}
	public int getTotalBlock() {
		return totalBlock;
	}
	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}
	
	public void makeRow() {
		this.startRow= (getCurPage()-1)*getPerPage()+1;
		this.lastRow= getCurPage()*getPerPage();
	}
	
				//호출할때 전체의 갯수를 달라고 할수도 있음.
	public void makePage(int totalCount) {
		//1.totalPage
		int totalPage= totalCount/perPage;  //나머지가 0이 아닌걸 
		if(totalCount%perPage != 0) { //totalPage%perPage !=0일때, totalCount/perpage에 +1 이니까 totalPage++; 라고 하는게 맞다.
			totalPage++;
		}
		
		//2.totalBlock //우리가 정하는것.
		this.totalBlock = totalPage/perBlock;
		if(totalPage%perBlock !=0) {
			totalBlock++;
		}
		
		//3.curPage로 curBlock : 현재지금 몇번 블럭에 있는지 알기. 현재 번호를 찾기 1-5는 1블럭 6-10 2블럭
		this.curBlock= curPage/perBlock; //나머지가 0이 아니라면
		if(curPage%perBlock !=0) {
			curBlock++;
		}
		
		//4.curBlock로 startNum,lastNum 구하기
		this.startNum=(curBlock-1)*perBlock+1;
		this.lastNum=curBlock*perBlock;
		
		//5.curBlock(현재블럭)이 마지막 block일때
		if(curBlock == totalBlock) {
			this.lastNum=totalPage;
		}
		
	}
	
	
}
