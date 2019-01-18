package pl.coderslab.model;

import java.sql.Timestamp;
import java.util.List;

public class PlanWithDetails extends Plan {
    //Dziedziczy po Plan i dodaje szczegóły planu
    private List<PlanDetail> planDetailList;

    public PlanWithDetails(){}

    public PlanWithDetails(String name, String description, Timestamp created, int admin_id, List<PlanDetail> planDetailList) {
        super(name, description, created, admin_id);
        this.planDetailList = planDetailList;
    }

    public List<PlanDetail> getPlanDetailList() {
        return planDetailList;
    }

    public void setPlanDetailList(List<PlanDetail> planDetailList) {
        this.planDetailList = planDetailList;
    }

    public void addPlanDetail(PlanDetail planDetail) {
        this.planDetailList.add(planDetail);
    }
}
