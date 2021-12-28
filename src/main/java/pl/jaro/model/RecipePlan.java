package pl.jaro.model;

public class RecipePlan {

    private Integer id;
    private Integer recipeId;
    private String mealName;
    private Integer displayOrder;
    private Integer dayNameId;
    private Integer planId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Integer getDayNameId() {
        return dayNameId;
    }

    public void setDayNameId(Integer dayNameId) {
        this.dayNameId = dayNameId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public RecipePlan(){}

    public RecipePlan(Integer recipeId, String mealName, Integer displayOrder, Integer dayNameId, Integer planId) {
        this.recipeId = recipeId;
        this.mealName = mealName;
        this.displayOrder = displayOrder;
        this.dayNameId = dayNameId;
        this.planId = planId;
    }

    @Override
    public String toString() {
        return "RecipePlan{" +
                "id=" + id +
                ", recipeId=" + recipeId +
                ", mealName='" + mealName + '\'' +
                ", displayOrder=" + displayOrder +
                ", dayNameId=" + dayNameId +
                ", planId=" + planId +
                '}';
    }
}
