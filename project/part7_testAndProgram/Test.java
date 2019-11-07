package project.part7_testAndProgram;

import project.part2_beans.Category;
import project.part2_beans.Company;
import project.part2_beans.Coupon;
import project.part2_beans.Customer;
import project.part5_businessLogistics.AdminFacade;
import project.part5_businessLogistics.CompanyFacade;
import project.part5_businessLogistics.CouponExpirationDailyJob;
import project.part5_businessLogistics.CustomerFacade;
import project.part6_login.ClientType;
import project.part6_login.LoginManager;

public class Test {
	/**
	 * presents all the capabilities of the system accompanied by notes.
	 */
	public static void testAll() {
		try {
			
			CouponExpirationDailyJob job = new CouponExpirationDailyJob();
			Thread tread = new Thread(job);
			tread.start();
			
			System.out.println("The administrator logs in and inserts four companies.");
			AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().login(ClientType.ADMINISTRATOR, "admin@admin.com", "admin");
			adminFacade.addCompany(new Company(1, "superpharm", "superpharm@gmail.com", "superpharm123", null));
			adminFacade.addCompany(new Company(2, "nmc", "nmc@gmail.com", "nmc123", null));
			adminFacade.addCompany(new Company(3, "lev", "lev@gmail.com", "lev123", null));
			adminFacade.addCompany(new Company(4, "arcadia", "arcadia@gmail.com", "arcadia123", null));
			System.out.println("These are the four companies");
			System.out.println();
			adminFacade.allCompaniesDisplay();
			System.out.println();
			
			System.out.println("The administrator updates nmc password to nmc1234");
			adminFacade.updateCompany(new Company(2, "nmc", "nmc@gmail.com", "nmc1234", null));
			System.out.println("These are company details after the update:");
			adminFacade.oneCompanyDisplay(2);
			System.out.println();
			

			
			System.out.println("The administrator inserts four customers into the system");
			adminFacade.addCustomer(new Customer(1, "ori", "tal", "ori@gmail.com", "ori123", null));
			adminFacade.addCustomer(new Customer(2, "orna", "davidson", "orna@gmail.com", "orna123", null));
			adminFacade.addCustomer(new Customer(3, "david", "davidson", "david@gmail.com", "david123", null));
			adminFacade.addCustomer(new Customer(4, "yair", "dotan", "yair@gmail.com", "yair123", null));
			System.out.println("These are the four customers");
			System.out.println();
			adminFacade.allCustomersDisplay();
			System.out.println();
			
			System.out.println("Orna divorced her husband and changed her name back to her original name");
			System.out.println("The administrator updates Orna's details");
			adminFacade.updateCustomer(new Customer(2, "orna", "adler", "orna@gmail.com", "orna123", null));
			System.out.println("These are Orna's details after the update");
			adminFacade.oneCustomerDisplay(2);
			System.out.println();
			

			
			System.out.println("Now we will move on to the business logistics of the company facade class");
			System.out.println();
			
			System.out.println("The four companies logs in and insert their coupons");
			CompanyFacade superpharmFacade = (CompanyFacade) LoginManager.getInstance().login(ClientType.COMPANY, "superpharm@gmail.com", "superpharm123");
			CompanyFacade levFacade = (CompanyFacade)LoginManager.getInstance().login(ClientType.COMPANY, "lev@gmail.com", "lev123");
			CompanyFacade arcadiaFacade = (CompanyFacade)LoginManager.getInstance().login(ClientType.COMPANY, "arcadia@gmail.com", "arcadia123");
			CompanyFacade nmcFacade = (CompanyFacade)LoginManager.getInstance().login(ClientType.COMPANY, "nmc@gmail.com", "nmc1234");
			Coupon coupon1 = new Coupon(1, 1, Category.COSMETICS, "careline lipstick", "2 units of careline lipstick matte color", "2019-12-12", "2020-12-12", 200, 50, "image");
			Coupon coupon2 = new Coupon(2, 1, Category.COSMETICS, "careline face soup", "2 units of careline face soup", "2019-12-12", "2020-12-12", 200, 50, "image");
			Coupon coupon3 = new Coupon(3, 1, Category.FOOD, "materna", "2 units of materna Substitute breast milk", "2019-12-12", "2020-12-12", 200, 50, "image");
			Coupon coupon4 = new Coupon(4, 3, Category.CINEMA, "movie ticket", "one movie ticket at lev cinema", "2019-07-01", "2019-10-01", 1000, 25, "image");
			Coupon coupon5 = new Coupon(5, 3, Category.CINEMA, "subscribe", "Subscribe to 20 movies at Lev Cinema", "2019-07-01", "2019-10-01", 1000, 400, "image");
			Coupon coupon6 = new Coupon(6, 4, Category.GAMES, "ps4 game", "one ps4 game included on summer sale", "2019-07-01", "2019-10-01", 1000, 99, "image");
			Coupon coupon7 = new Coupon(7, 4, Category.GAMES, "xboxone game", "one xboxone game included on summer sale", "2019-07-01", "2019-10-01", 1000, 99, "image");
			Coupon coupon8 = new Coupon(8, 2, Category.MUSIC, "disc", "one disc included on summer sale", "2019-07-01", "2019-10-01", 2000, 29.90, "image");
			superpharmFacade.addCoupon(coupon1);
			superpharmFacade.addCoupon(coupon2);
			superpharmFacade.addCoupon(coupon3);
			levFacade.addCoupon(coupon4);
			levFacade.addCoupon(coupon5);
			arcadiaFacade.addCoupon(coupon6);
			arcadiaFacade.addCoupon(coupon7);
			nmcFacade.addCoupon(coupon8);
			System.out.println("These are the details of all the companies after adding the coupons");
			adminFacade.allCompaniesDisplay();
			System.out.println();
			
			System.out.println("Superpharm are updating the price of their third coupon");
			superpharmFacade.updateCoupon(new Coupon(3, 1, Category.FOOD, "materna", "2 units of materna Substitute breast milk", "2019-12-12", "2020-12-12", 200, 80, "image"));
			System.out.println("Coupon details after update:");
			superpharmFacade.oneCouponDisplay(3);
			System.out.println();
			

			
			System.out.println("These are all superpharm coupons");
			superpharmFacade.allCompanyCouponsDisplay();
			System.out.println();
			
			System.out.println("These are all superpharm coupons from food category:");
			superpharmFacade.companyCouponsFromSpecificCategoryDisplay(Category.FOOD);
			System.out.println();
			
			System.out.println("These are all superpharm coupons at or below the maximum price of 50 NIS:");
			superpharmFacade.companyCouponsAtOrBelowTheMaximumPriceDisplay(50);
			System.out.println();
			
			System.out.println("These are superpharm details:");
			superpharmFacade.companyDetailsDisplay();
			System.out.println();
			
			System.out.println("Now we will move on to the business logistics of the customer facade class");
			System.out.println();
			
			System.out.println("The four customers logs in and purchase coupons");
			CustomerFacade oriFacade = (CustomerFacade) LoginManager.getInstance().login(ClientType.CUSTOMER, "ori@gmail.com", "ori123");
			CustomerFacade ornaFacade = (CustomerFacade)LoginManager.getInstance().login(ClientType.CUSTOMER, "orna@gmail.com", "orna123");
			CustomerFacade davidFacade = (CustomerFacade)LoginManager.getInstance().login(ClientType.CUSTOMER, "david@gmail.com", "david123");
			CustomerFacade yairFacade = (CustomerFacade)LoginManager.getInstance().login(ClientType.CUSTOMER, "yair@gmail.com", "yair123");
			oriFacade.purchaseCoupon(coupon4);
			oriFacade.purchaseCoupon(coupon5);
			oriFacade.purchaseCoupon(coupon6);
			oriFacade.purchaseCoupon(coupon7);
			ornaFacade.purchaseCoupon(coupon1);
			ornaFacade.purchaseCoupon(coupon2);
			ornaFacade.purchaseCoupon(coupon3);
			ornaFacade.purchaseCoupon(coupon8);
			davidFacade.purchaseCoupon(coupon8);
			davidFacade.purchaseCoupon(coupon4);
			davidFacade.purchaseCoupon(coupon5);
			yairFacade.purchaseCoupon(coupon6);
			yairFacade.purchaseCoupon(coupon7);
			System.out.println("These are customers details (after purchase):");
			adminFacade.allCustomersDisplay();
			System.out.println();
			
			
			System.out.println("These are ori's coupons:");
			oriFacade.customersCouponsDisplay();
			System.out.println();
			
			System.out.println("These are ori's coupons from games category:");
			oriFacade.customerCouponsFromSpecificCategoryDisplay(Category.GAMES);
			System.out.println();
			
			System.out.println("These are ori's coupons at or below the maximum price of 99 NIS:");
			oriFacade.customerCouponsAtOrBelowTheMaximumPriceDisplay(99);
			System.out.println();
			
			System.out.println("These are ori's details:");
			oriFacade.customerDetailsDisplay();
			System.out.println();
			
			System.out.println("Finally, we will proceed to a demonstration of the deletion functions");
			System.out.println();
			
			System.out.println("Before we delete anything, let's review the purchase history of all coupons:");
			adminFacade.allPurchasesDisplay();
			System.out.println();
			
			System.out.println("The administrator deletes nmc");
			adminFacade.deleteCompany(2);
			System.out.println("These are all the remaining companies in the system (after deleting nmc):");
			adminFacade.allCompaniesDisplay();
			System.out.println();
			System.out.println("This is the history of purchases after deletion");
			adminFacade.allPurchasesDisplay();
			System.out.println();
			
			System.out.println("The administrator deletes david davidson");
			adminFacade.deleteCustomer(3);
			System.out.println("These are all the remaining customers in the system (after deleting david):");
			adminFacade.allCustomersDisplay();
			System.out.println();
			System.out.println("This is the history of purchases after deletion");
			adminFacade.allPurchasesDisplay();
			System.out.println();
			
			System.out.println("Arcadia deletes their second coupon");
			arcadiaFacade.deleteCoupon(7);
			System.out.println("These are all the remaining coupons in the system (after deleting coupon):");
			adminFacade.allCouponsDisplay();
			System.out.println();
			System.out.println("This is the history of purchases after deletion");
			adminFacade.allPurchasesDisplay();
			System.out.println();
			
			System.out.println("end");
			
			job.stop();
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
