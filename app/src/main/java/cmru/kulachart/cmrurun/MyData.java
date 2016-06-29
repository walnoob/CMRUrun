package cmru.kulachart.cmrurun;

/**
 * Created by jann on 28/6/2559.
 */
public class MyData {
    //explicet
    private int[] avatarInts = new int[]{
            R.drawable.bird48, R.drawable.doremon48, R.drawable.kon48, R.drawable.nobita48, R.drawable.rat48
    };

    private double[] latStationDoubles = new double[]{18.807690, 18.805526, 18.807821, 18.807659};
    private double[] lngStationDoubles = new double[]{98.985719, 98.985810, 98.987419, 98.988674};

    private int[] iconStationInts = new int[]{R.drawable.build1, R.drawable.build2, R.drawable.build3, R.drawable.build4};

    public int[] getIconStationInts() {
        return iconStationInts;
    }

    public int[] getAvatarInts() {

        return avatarInts;
    }

    public double[] getLatStationDoubles() {
        return latStationDoubles;
    }

    public double[] getLngStationDoubles() {
        return lngStationDoubles;
    }
}//mydata main class
