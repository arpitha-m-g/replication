node {
    stage("Checkout scm") {
      checkout scm
    }
    stage("Display output"){ 
	    sh "export PATH=$PATH:/google-cloud-sdk/bin"
 	    sh "/google-cloud-sdk/bin/gcloud config list"
	    def out_data = '{"result": "'+"${currentBuild.currentResult}"+'", "environment":"'+"${params.environment}" +'" }'
	    writeFile (file: 'outpur_file.txt', text: out_data)	   
    }
}
