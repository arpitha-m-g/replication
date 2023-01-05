node {
    stage("Checkout scm") {
      checkout scm
    }
    stage("Display output"){ 
	    withCredentials([[$class: 'FileBinding', credentialsId: 'project-series-01', variable: 'JSON_KEY']]) {
       	    sh "gcloud auth activate-service-account --key-file=${JSON_KEY}"}
 	    sh "/google-cloud-sdk/bin/gcloud config set project project-series-01"	       
	    sh "/google-cloud-sdk/bin/gcloud config list"
	    def out_data = '{"result": "'+"${currentBuild.currentResult}"+'" }'
	    writeFile (file: 'output_file.json', text: out_data)	
	    sh "/google-cloud-sdk/bin/bq load --source_format NEWLINE_DELIMITED_JSON project-series-01.dataset1.jenkins_output gs://jenkins_output_format/output_format.json output_file.json "
    }
}
