echo CURRENT_DIR = $CURRENT_DIR
CYG_GIT_FOLDER=$(cygpath -u  $CURRENT_DIR/git)
echo CYG_GIT_FOLDER = $CYG_GIT_FOLDER
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
echo SCRIPT_DIR = $SCRIPT_DIR
JOBS_DIR=$SCRIPT_DIR/../jobs
echo JOBS_DIR = $JOBS_DIR
TEMPLATE=eu.operando.int.jnk.tsk.pre.xml
echo TEMPLATE = $TEMPLATE

find $CYG_GIT_FOLDER -type f -iname "JenkinsfileDockerBuild" -print0 | while IFS= read -r -d $'\0' line; do
    echo "$line"
    # https://stackoverflow.com/questions/13373249/extract-substring-using-regexp-in-plain-bash
	# https://stackoverflow.com/questions/1891797/capturing-groups-from-a-grep-regex
    regex=".*/git/([^/]*)/(.*)/(.*)"
	if [[ $line =~ $regex ]]
	then
		SUBREPO="${BASH_REMATCH[1]}"
		REPOPATH="${BASH_REMATCH[2]}"
		JENKINSFILENAME="${BASH_REMATCH[3]}"
		regexname=".*/(.*).src.dvp"
		if [[ $REPOPATH =~ $regexname ]]
		then
			NAME="${BASH_REMATCH[1]}.jnk.tsk"
			echo SUBREPO=$SUBREPO
			echo REPOPATH=$REPOPATH
			echo NAME=$NAME
			echo JENKINSFILENAME=$JENKINSFILENAME
			if [ "$TEMPLATE" != "$NAME.xml" ]
			then
				cp $JOBS_DIR/$TEMPLATE $JOBS_DIR/$NAME.xml
				# we change the sed delimiter character
				sed -i -e "s#op-core</url>#$SUBREPO</url>#g" -e "s#<path>.*</path>#<path>$REPOPATH</path>#g" -e "s#<scriptPath>.*</scriptPath>#<scriptPath>$REPOPATH/$JENKINSFILENAME</scriptPath>#g" $JOBS_DIR/$NAME.xml
			fi
		fi
	fi
done

