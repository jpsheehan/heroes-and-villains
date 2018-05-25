for f in ../original-images/*.jpg; do
	newFilename=../src/images/$(basename -- $f);
	if [ -a $newFilename ]; then
		echo skipping $newFilename
	else
		echo converting $newFilename
		convert -strip -interlace Plane -gaussian-blur 0.05 -quality 50% -resize 400x400 $f ../src/images/$(basename -- $f)
	fi
done

