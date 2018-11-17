enum ReportType{
    DAILY,INVENTORY
}

public class Report {
    private String[] lines;
    private ReportType type;

    public Report(ReportType t){
        this.type=t;
    }

    public ReportType getType(){
        return this.type;
    }

    public void setLines(){

    }
}


