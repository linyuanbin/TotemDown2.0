package model;

public class Manager {
	
	private String mIdCard;  //����Ա���֤
	private String mName; //����Ա����
	private String mPassword;   //����
	private String mEmail; //����
	private String mTel;//����Ա�ֻ���
	private String mHeadPort; //ͷ��
	private String mSex;
	private int mAge;
	private String mWorkNum;//Ա����
	
	
	public String getmIdCard() {
		return mIdCard;
	}
	public void setmIdCard(String mIdCard) {
		this.mIdCard = mIdCard;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmPassword() {
		return mPassword;
	}
	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public String getmTel() {
		return mTel;
	}
	public void setmTel(String mTel) {
		this.mTel = mTel;
	}
	public String getmHeadPort() {
		return mHeadPort;
	}
	public void setmHeadPort(String mHeadPort) {
		this.mHeadPort = mHeadPort;
	}
	public String getmSex() {
		return mSex;
	}
	public void setmSex(String mSex) {
		this.mSex = mSex;
	}
	public int getmAge() {
		return mAge;
	}
	public void setmAge(int mAge) {
		this.mAge = mAge;
	}
	public String getmWorkNum() {
		return mWorkNum;
	}
	public void setmWorkNum(String mWorkNum) {
		this.mWorkNum = mWorkNum;
	}
	
	@Override
	public String toString() {
		return "Manager [mIdCard=" + mIdCard + ", mName=" + mName + ", mPassword=" + mPassword + ", mEmail=" + mEmail
				+ ", mTel=" + mTel + ", mHeadPort=" + mHeadPort + ", mSex=" + mSex + ", mAge=" + mAge + ", mWorkNum="
				+ mWorkNum + "]";
	}
	
	
	
}
