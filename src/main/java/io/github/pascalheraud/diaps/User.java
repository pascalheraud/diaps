package io.github.pascalheraud.diaps;

import java.util.Date;

import org.springframework.data.relational.core.mapping.Table;

@Table("livi_user")
public class User {

	public enum DriverFunnelStepType {
		ONEGO_GEOLOC_NOPROOF, ONEGO_NOGEOLOC, ONEGO_GEOLOC_PROOF, ONETRIP_NOGO, TWOGO_GEOLOC_ONEPROOFNOTLAST, TWOGO_GEOLOC_ONEPROOFLAST, DSIUNLOCKED_NOBOOKING, PASTBOOKINGS_NOBONUS, DSIUNLOCKED_TRIPSONAVAILABLEDSI_NOBOOKING
	}

	public Long id;
	public String email;
	public String firstName;
	public String lastName;
	public Date dateOfBirth;
	public Date creationDate;
	public DriverFunnelStepType driverFunnelStepType;
}
