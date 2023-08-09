# Secret-Engine
**Vault Engine have the following features:**
User Sign-Up : Any unregistered user can create an account.
Secret Message storage : User can able to store multiple secret messages securely in the Vault Engine.
Access Control : The Vault Engine enforce access control to ensure that only authoried users can access to the stored secret messages.
Encryption : Vault Engine uses ascii values for encryption and to protect the confidentiality of stored messages.

**Implementation:**
Encryption:
	In this project, we use the Ascii values to encode the user’s secret message. We have created a class with name “EncodeAndDecode” . In that class we created two methods one is to encode the secret message using the ascii values and another method is to decode the secret message when required. When the user enters the password , the password is encrypted by the password encoder. The encrypted password is stored in the database.

**Steps for local setup:**
git clone this repo.
Import to STS (use existing maven projects to import)
update dependencies
change database credentials in the application properties.
Change database name accordingly in application properties.


Here are the apis which are used in the application:

Add Secret message : localhost:8080/secretmessage/add/1  {existing userid}
Payload: 
	{
    	"name":"secret",
    	"message": "This is secret message"
	}
 Response:
 	{
	    "id": 12,
	    "name": "secret",
	    "message": "Uijt!jt!tfdsfu!nfttbhf",
	    "user": {
	        "id": 1,
	        "username": "Sai",
	        "password": "$2a$10$SvMV9laSWKSIs.G2aSaTn.PZr6czeJAQo7uleysHPgintOirHAVSC",
	        "role": "user",
	        "enabled": true,
	        "credentialsNonExpired": true,
	        "accountNonExpired": true,
	        "authorities": [
	            {
	                "authority": "user"
	            }
	        ],
	        "accountNonLocked": true
	    }
	}
 


