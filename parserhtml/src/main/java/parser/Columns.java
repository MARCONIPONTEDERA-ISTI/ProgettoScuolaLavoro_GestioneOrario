package parser;

public class Columns {
 private Integer c;
 private boolean isFirst = false;
 
 
 
 
public Columns() {
	isFirst = false;
}
public Integer getC() {
	return c;
}
public void setC(Integer c) {
	this.c = c;
}
public boolean isFirst() {
	return isFirst;
}
public void setFirst(boolean isFirst) {
	this.isFirst = isFirst;
}
@Override
public String toString() {
	return "Columns [c=" + c + ", isFirst=" + isFirst + "]";
}

@Override
public boolean equals(Object obj) {

	Columns other = (Columns) obj;
	if (c == null) {
		if (other.c != null)
			return false;
	} else if (!c.equals(other.getC()))
		return false;
	return true;
}

 
 
}
