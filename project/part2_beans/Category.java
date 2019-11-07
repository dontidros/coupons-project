package project.part2_beans;
public enum Category {
		FOOD,
		ELECTRICITY,
		RESTAURANT,
		VACATION,
		COSMETICS,
		BOOKS,
		GAMES,
		MUSIC,
		CINEMA,
		THEATER;
		
		public static Category getCategoryByString(String str) {
			return Enum.valueOf(Category.class, str);
			}
			public static String getStringByCategory(Category color) {
			return color.toString();
			}

}
