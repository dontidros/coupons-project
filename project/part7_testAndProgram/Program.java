package project.part7_testAndProgram;

import project.part1_DB.DB;
import project.part2_beans.Category;
import project.part2_beans.Coupon;
import project.part5_businessLogistics.AdminFacade;
import project.part5_businessLogistics.CompanyFacade;
import project.part5_businessLogistics.CustomerFacade;
import project.part6_login.ClientType;
import project.part6_login.LoginManager;


@SuppressWarnings("unused")
public class Program {


	@SuppressWarnings("static-access")
	public static void main(String[] args) {

		try {
			
			DB.buildDB();
			Test test = new Test();
			test.testAll();
			
//			CompanyFacade superpharmFacade = (CompanyFacade) LoginManager.getInstance().login(ClientType.COMPANY, "superpharm@gmail.com", "superpharm123");;
//			String json = superpharmFacade.getCompanyCouponsAtOrBelowTheMaximumPriceJSON(50);
//			System.out.println(json);
//			superpharmFacade.allCompanyCouponsDisplay();
//			CustomerFacade oriFacade = (CustomerFacade) LoginManager.getInstance().login(ClientType.CUSTOMER, "ori@gmail.com", "ori123");
//			Coupon coupon3 = new Coupon(3, 1, Category.FOOD, "materna", "2 units of materna Substitute breast milk", "2018-12-12", "2019-12-12", 200, 50, "image");
//			oriFacade.purchaseCoupon(coupon3);
			
		}
		
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			
			
		}
	}

}
