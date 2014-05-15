<?php
    $names = array(
	"Alex",
	"Berty",
	"Carl",
	"David",
	"Erine",
	"Fridrich",
	"Gilian",
	"Honda",
        "John",
	"James"
    );

    $requestedText =  $_POST["text"];
    if(empty($requestedText)) {
        echo "?";
	return;
    }

    $text = strtolower($requestedText);
    $textLength = strlen($requestedText);
    $responseText = "?";
    foreach($names as $name) {
        if(stristr($text, substr($name, 0, $textLength))) {
            $responseText =  "$name";
            break;
        }
    }

    echo $responseText;
?>
