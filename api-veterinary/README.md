	profilemaster
	=============
	
	PROGRAMADORES CHILE - PROFILE MASTER CRUD API
	- - - - - - - - - - - - - - - - - - - - - - -

    Accessories:
    - databasedocs 
    - postman
		
	List of operations and their description:

    Profile:

	1. ../profile/all                                              GET : Get all profiles 
	2. ../profile/{id}                                             GET : Get profile by id 
    3. ../profile/city/{city}                                      GET : Get profiles by city
    4. ../profile/country/{country}                                GET : Get profiles by country
    5. ../profile/changeOfAddress/{changeOfAddress}                GET : Get profiles by changeOfAddress
    6. ../profile/typeEducation/{typeEducation}                    GET : Get profiles by typeEducation
    7. ../profile/technology/{technology}                          GET : Get profiles by technology
    8. ../profile/experienceOfYears/{years}                        GET : Get profiles by experience of years

    Person:

	1. ../person/all                                               GET : Get all persons
	2. ../person/{id}                                              GET : Get person by id
	3. ../person/city/{city}                                       GET : Get person by city
	4. ../person/country/{country}                                 GET : Get person by country
	5. ../person/save                                             POST : Save person
	6. ../person/delete/{id}                                    DELETE : Delete person by id

    Technology:

	1. ../technology/all                                           GET : Get all Technologies
	2. ../technology/{idPerson}/{idTechnology}                     GET : Get technology by id
	3. ../technology/{idPerson}                                    GET : Get technology by idPerson
	4. ../technology/technology/{technology}                       GET : Get technology by technology
	5. ../technology/save                                         POST : Save technology
	6. ../technology/delete/{idPerson}/{idTechnology}           DELETE : Delete technology by id

    Work Experience:

	1. ../work-experience/all                                      GET : Get all work experiences
	2. ../work-experience/{idPerson}/{idWorkExperience}            GET : Get work experience by id
	3. ../work-experience/{idPerson}                               GET : Get work experience by idPerson
	4. ../work-experience/save                                    POST : Save work experience
	5. ../work-experience/delete/{idPerson}/{idWorkExperience}  DELETE : Delete work experience by id

    Academic Information:

	1. ../academic-info/all                                        GET : Get all academic info
	2. ../academic-info/{idPerson}/{idAcademicInfo}                GET : Get academic info by id
	3. ../academic-info/{idPerson}                                 GET : Get academic info by idPerson
	4. ../academic-info/typeEducation/{typeEducation}              GET : Get academic info by typeEducation
	5. ../academic-info/save                                      POST : Save academic info
	6. ../academic-info/delete/{idPerson}/{idAcademicInfo}      DELETE : Delete academic info by id
