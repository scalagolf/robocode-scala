package scalagolf

import robocode.Robot
import java.awt.Color
import robocode.HitRobotEvent
import robocode.ScannedRobotEvent
import robocode.BorderSentry
import robocode.AdvancedRobot
import robocode.HitWallEvent
import robocode.BulletHitEvent


class LukasWall2  extends AdvancedRobot {

  var peek = false;
  var moveAmount = 0.0
  var turnCounter = 0
  
  var power = 5;
  
  
  override def run() : Unit = {
    setBodyColor(Color.black)
		setGunColor(Color.black)
		setRadarColor(Color.orange)
		setBulletColor(Color.red)
		setScanColor(Color.cyan)
		
		// Initialize moveAmount to the maximum possible for this battlefield.
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight()) / 5 - 1
		// Initialize peek to false
		peek = false
		
		turnLeft(getHeading() % 90)
		ahead(moveAmount * 5)
	
		peek = true
		turnGunRight(90)
		turnRight(90)

		while (true) {
			// Look before we turn when ahead() completes.
			peek = true
			// Move up the wall
			ahead(moveAmount)
			scan()
			turnCounter += 1
			ahead(moveAmount)
			scan()
			turnCounter += 1
			ahead(moveAmount)
			scan()
			turnCounter += 1
			ahead(moveAmount)
			scan()
			turnCounter += 1
			ahead(moveAmount)
			scan()
			turnCounter += 1
			  
			// Don't look now
			peek = false
			// Turn to the next wall
			turnRight(90)
			
		}
  }
  
  override def onHitByBullet(e:BulletHitEvent) : Unit = {
    turnRight(90)
    
  }
  
  override def onHitWall(e:HitWallEvent) : Unit = {
    back(1);
  }
  
  override def onHitRobot(e:HitRobotEvent) : Unit = {
    if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(100)
		} // else he's in back of us, so set ahead a bit.
		else {
			ahead(100)
		}
  }
  
  override def onScannedRobot(e:ScannedRobotEvent) : Unit = {
    var random = Math.random();
    if( turnCounter < 200) {
      if( random < 0.3 ) {
        fire(power)
      }
    } else if( turnCounter < 300 ) {
      if( random < 0.5 ) {
        fire(power)
      }
    } else if( turnCounter < 500 ) {
      if( random < 0.7 ) {
        fire(power)
      }
    } else {
      fire(power)
    }
  }
}