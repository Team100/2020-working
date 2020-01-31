package frc.robot.FRCLib.Motors;

import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class FRCVictorSPX {
    private int canID;
    private boolean inverted;
    private VictorSPX motor;
    private FRCTalonSRX master;

    public FRCVictorSPX enableFollowing(){
        motor.follow(master.getMotor());
        return this;
    }

    public int getCanID() {
        return canID;
    }

    public void setCanID(int canID) {
        this.canID = canID;
    }

    public boolean isInverted() {
        return inverted;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public VictorSPX getMotor() {
        return motor;
    }

    public void setMotor(VictorSPX motor) {
        this.motor = motor;
    }

    public FRCTalonSRX getMaster() {
        return master;
    }

    public void setMaster(FRCTalonSRX master) {
        this.master = master;
    }


    public static final class FRCVictorSPXBuilder {
        private int canID;
        private boolean inverted;
        private FRCTalonSRX master;

        private FRCVictorSPXBuilder() {
        }

        public static FRCVictorSPXBuilder aFRCVictorSPX() {
            return new FRCVictorSPXBuilder();
        }

        public FRCVictorSPXBuilder withCanID(int canID) {
            this.canID = canID;
            return this;
        }

        public FRCVictorSPXBuilder withInverted(boolean inverted) {
            this.inverted = inverted;
            return this;
        }

        public FRCVictorSPXBuilder withMaster(FRCTalonSRX master) {
            this.master = master;
            return this;
        }

        public FRCVictorSPX build() {
            FRCVictorSPX fRCVictorSPX = new FRCVictorSPX();
            fRCVictorSPX.setCanID(canID);
            fRCVictorSPX.setInverted(inverted);
            fRCVictorSPX.setMaster(master);
            return fRCVictorSPX;
        }
    }
}
