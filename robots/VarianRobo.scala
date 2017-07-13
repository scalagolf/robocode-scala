package scalagolf

import robocode._;
import robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.Color;


class VarianRobo extends Robot {
    var trackName:String = null
    var gunTurnAmt:Double = 0
    var count:Int = 0
    override def run() {
	setBodyColor(new Color(255, 255, 255))
	setGunColor(new Color(255, 255, 255))
	setRadarColor(new Color(255, 255, 255))
	setBulletColor(new Color(255, 255, 255))
	setScanColor(new Color(255, 255, 255))

         while (true) {
             ahead(100)
             turnGunRight(360)
             back(100)
             turnGunRight(360)
         }
    }

    override def onScannedRobot(e:ScannedRobotEvent) {
       trackName = e.getName()
		// This is our target.  Reset count (see the run method)
		count = 0
		// If our target is too far away, turn and move toward it.
		if (e.getDistance() > 150) {
			gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));

			turnGunRight(gunTurnAmt); // Try changing these to setTurnGunRight,
			turnRight(e.getBearing()); // and see how much Tracker improves...
			// (you'll have to make Tracker an AdvancedRobot)
			ahead(e.getDistance() - 140);
			return;
		}

		// Our target is close.
		gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
		turnGunRight(gunTurnAmt);
		fire(3);

		// Our target is too close!  Back up.
		if (e.getDistance() < 100) {
			if (e.getBearing() > -90 && e.getBearing() <= 90) {
				back(40);
			} else {
				ahead(40);
			}
		}
		scan();
       fire(1)
    }
}
