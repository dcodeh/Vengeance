
public class USSVengeance extends Ship{
	
	private double impulseHealth;
	private double warpHealth;
	private double sensorHealth;
	private double shieldGenHealth;
	private double secondShieldGenHealth;
	private double computerHealth;
	private double phaserHealth;
	private double photonHealth;
	private double hullIntegrity;
	private boolean crewAlive;
	private boolean captainAlive;
	private double shieldIntegrity;
	private boolean shieldsUp;
	
	public USSVengeance(){
		super();
		
		impulseHealth = 100.0;
		warpHealth = 100.0;
		warpHealth = 100.0;
		sensorHealth = 100.0;
		shieldGenHealth = 100.0;
		secondShieldGenHealth = 100.0;
		computerHealth = 100.0;
		phaserHealth = 100.0;
		photonHealth = 100.0;
		hullIntegrity = 100.0;
		crewAlive = true;
		captainAlive = true;
		shieldIntegrity = 100.0;
		shieldsUp = true;
	}
	
	public String damageSystem(String system, double intensity){
		String ret = "hull";
		
		if(system.contains("impulse")){
			impulseHealth -= intensity;
			ret = "impulse engines";
		} else if(system.contains("warp")){
			warpHealth -= intensity;
			ret = "warp engines";
		} else if(system.contains("sensor")){
			sensorHealth -= intensity;
			ret = "sensor array";
		} else if(system.contains("shield")){
			if(shieldGenHealth > 0){
				shieldGenHealth -= intensity;
				ret = "main shield generator";
			}
			
			if(shieldGenHealth < 0){
				secondShieldGenHealth -= (shieldGenHealth * -1);
				shieldGenHealth = 0;
				ret = "secondary shield generator";
			}
			
			if((shieldGenHealth <= 0) && (secondShieldGenHealth <= 0)){
				shieldsUp = false;
			}
			
		} else if(system.contains("computer")){
			computerHealth -= intensity;
			ret = "computer systems";
		} else if(system.contains("phaser")){
			phaserHealth -= intensity;
			ret = "phaser banks";
		} else if(system.contains("photon")){
			photonHealth -= intensity;
			ret = "photon torpedoes";
		} else {
			hullIntegrity -= intensity;
			ret = "the hull";
		}
		
		return system;
	}
	
	public String damageVengeance(double intensity, String system){
		system = system.toLowerCase();
		String ret = "";
		int dif = 0;
		if(shieldsUp){
			shieldIntegrity -= intensity;
			ret = system + "...Vengeance shields are holding";
			if(shieldIntegrity < 0){
				dif = (int) (shieldIntegrity * -1);
				shieldIntegrity = 0;
				ret = system + "...Vengeance shields penetrated!";
			}
			
			damageSystem(system, dif);
			
		} else {
			system = damageSystem(system, intensity);
			ret = system + "...Vengeance shields penetrated!";
		}
		
		return ret;
	}
	
}