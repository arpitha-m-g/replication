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
				script: '''
					import groovy.json.JsonSlurper 
					//def namespaces= readFile "${env.WORKSPACE}/dummy.json"
					def jsonSlurper = new JsonSlurper()
					def object = jsonSlurper.parseText('{ "name": "John", "env" : "1"}')
					return object.env
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
	    get_namespace()
	    def out_data = '{"result": "'+"${currentBuild.currentResult}"+'", "environment":"'+"${params.environment}" +'" }'
	    writeFile (file: 'outpur_file.txt', text: out_data)	   
    }
}
def get_namespace(){
	def namespaces= readFile "${env.WORKSPACE}/dummy.json"
	def jsonSlurper = new JsonSlurper()
	def object = jsonSlurper.parseText(namespaces)
	echo "Data is: ${object}"
	return object
}          
