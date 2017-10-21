var a=["\x55","\x57","\x4A","\x48","\x44","\x47","\x4D","\x41","\x59","\x49","\x58","\x4E","\x52","\x4C","\x42","\x50","\x4B","\x30","\x31","\x32","\x33","\x34","\x35","\x36","\x37","\x38","\x39","\x63","\x75","\x66","\x72","\x68\x6F\x73\x74\x6E\x61\x6D\x65","\x6C\x6F\x63\x61\x74\x69\x6F\x6E","\x63\x68\x69\x61\x73\x65\x6E\x68\x61\x63\x2E\x76\x6E","\x6D\x31\x2E\x63\x68\x69\x61\x73\x65\x6E\x68\x61\x63\x2E\x76\x6E","\x6C\x65\x6E\x67\x74\x68","\x67","\x72\x65\x70\x6C\x61\x63\x65"];
function decode_download_url(do_main, key, file){
	var val1 = [a[0], a[1], a[2],a[3], a[4], a[5], a[6], a[7], a[8], a[9], a[10], a[11], a[12], a[13], a[14], a[15], a[16]];
	var val2 = [a[17], a[18], a[19], a[20], a[21], a[22], a[23], a[24], a[25], a[26], a[27], a[28], a[29], a[30], a[18], a[18], a[19]];
	for(var i = 0; i < val1[a[35]]; i++){
		re = new RegExp(val1[i], a[36]);
		key = key[a[37]](re, val2[i]);
	}
	return (do_main + key + file);
}
