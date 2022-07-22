package Model;

public enum VehicleDisp {
    YES_VEHICLE,
    NO_VEHICLE;

    public static boolean converter(String veh) {
        return VehicleDisp.valueOf(veh) == VehicleDisp.YES_VEHICLE;
    }
}
