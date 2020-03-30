package pl.kwi.springboot.enums;

public enum SortingEnum {
	
	
	TITLE_INCREASING("title: increasing", "title_increasing"), TITLE_DECREASING("title: decreasing", "title_decreasing"), DATE_INCREASING("date: increasing", "date_increasing"), 
	DATE_DECREASING("date: decreasing", "date_decreasing"), PAIR_INCREASING("pair: increasing", "pair_increasing"), PAIR_DECREASING("pair: decreasing", "pair_decreasing");
	
	private String text;
	private String value;
		
	
	private SortingEnum(String text, String value) {
		this.text = text;
		this.value = value;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public static SortingEnum getEnum(String value) {
        for(SortingEnum v : values())
            if(v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
	

}
