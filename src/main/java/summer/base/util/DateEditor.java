package summer.base.util;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

/**
 * 功能概要：把从页面接受过来的字符格式，转成系统需要的日期类型
 * @author xyl
 *
 */
public class DateEditor extends PropertyEditorSupport {
	
	/**
	 * 日期格式
	 */
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");  
	/**
	 * 时间格式
	 */
    private static final DateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
  
    private DateFormat dateFormat;  
    private boolean allowEmpty = true; 
	
	public DateEditor() {  
    }  
  
    public DateEditor(DateFormat dateFormat) {  
        this.dateFormat = dateFormat;  
    } 

    /** 
     * Parse the Date from the given text, using the specified DateFormat. 
     */  
    @Override  
    public void setAsText(String text) throws IllegalArgumentException {  
        if (this.allowEmpty && !StringUtils.hasText(text)) {  
            // Treat empty String as null value.  
            setValue(null);  
        } else {  
            try {  
                if(this.dateFormat != null)  
                    setValue(this.dateFormat.parse(text));  
                else {  
                    if(text.contains(":"))  
                        setValue(TIME_FORMAT.parse(text));  
                    else  
                        setValue(DATE_FORMAT.parse(text));  
                }  
            } catch (ParseException ex) {  
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);  
            } 
        }  
    }  
  
    /** 
     * Format the Date as String, using the specified DateFormat. 
     */  
    @Override  
    public String getAsText() {  
        Date value = (Date) getValue();  
        DateFormat dateFormat = this.dateFormat;  
        if(dateFormat == null)  
            dateFormat = TIME_FORMAT;  
        return (value != null ? dateFormat.format(value) : "");  
    } 
}
