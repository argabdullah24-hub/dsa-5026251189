package lw01.unguided;

public class ProjectorRental extends Rental {
    public ProjectorRental(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        int p = getDays();
        int cost = 0; 
        
        if (p <= 3) {
            cost += p * 60000; 
        } else {
            cost += (3 * 60000) + ((p - 3) * 45000); 
        }
        return cost;
    }

    @Override
    public String label() {
        return "Projector"; 
    }
}
