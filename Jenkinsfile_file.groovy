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
        def json_output = '''
            {
             "author" : sh "echo ${env.BUILD_USER_ID}",
             "result": sh "echo ${currentbuild.duration}"
             }
             '''
	writeFile(file: 'output.json', text:json_output) 
    }
}
            
