#include <stdio.h>
#include "msd_ucdynarr.h"

int main(int argc, char* argv[]) {
	ucdynarr* ucda = msd_ucda();
	
	if ( argc < 2 ) {
		char c;
		while( (c = getchar()) != '\n' ) {
			msd_ucda_add(ucda, c);
		}
	} else {
		for(int i = 1; i < argc; i++) {
			for(int j = 0; argv[i][j] != '\0'; j++) {
				msd_ucda_add(ucda, argv[i][j]);
			}
		}
	}
	
	for(int i = 0; i < ucda->l; i++) {
		putchar(ucda->a[i]);
		putchar(' ');
	}

	msd_ucda_free(ucda);
}
