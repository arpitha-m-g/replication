node {
    stage("Checkout scm") {
      checkout scm
    }
    stage("Display output"){ 
	    withCredentials([[$class: 'FileBinding', credentialsId: 'project-series', variable: 'JSON_KEY']]) {
       	    sh "/google-cloud-sdk/bin/gcloud auth activate-service-account --key-file=${JSON_KEY}"}
 	    sh "/google-cloud-sdk/bin/gcloud config set project project-series-01"	       
	    sh "/google-cloud-sdk/bin/gcloud config list"
	    def out_data = """{ "results": "${currentBuild.currentResult}",
"job_id": "${env.BUILD_NUMBER}" }
"""
	    writeFile (file: 'output_file.json', text: out_data)	
	    sh "/google-cloud-sdk/bin/bq load --source_format NEWLINE_DELIMITED_JSON dataset1.output output_file.json results:json,job_id:json "
    }
}
