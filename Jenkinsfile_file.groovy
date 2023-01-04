import java.time.*
import java.time.format.DateTimeFormatter

properties([parameters([
    [$class: 'ChoiceParameter', 
	    choiceType: 'PT_SINGLE_SELECT', 
		filterLength: 1, 
		filterable: false, 
		name: 'environment', 
		randomName: 'choice-parameter-126266809345300', 
		script: [
		    $class: 'GroovyScript', 
			fallbackScript: [
			    classpath: [], 
				oldScript: '', 
				sandbox: false, 
				script: 
				        'return ["error"]'
			],
			script: [
			    classpath: [], 
				oldScript: '', 
				sandbox: false, 
				script: 
				        'return ["npe","uat","prod"]'
				]
			]
		]
	])
])
node {
    stage("Checkout scm") {
      checkout scm
    }
    stage("Display output"){
	    def data = "author" + env.BUILD_NUMBER
	    writeFile (file: 'outpur_file.txt', text: data)
	   
    }
}
            
