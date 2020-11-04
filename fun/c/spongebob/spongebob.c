#define  RND_IMPLEMENTATION
#include "rnd.h"

#include "msd_ucdynarr.h"

#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <time.h>


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
	
	/* spongebobify */

	time_t t;
	
	rnd_xorshift_t rnd;
	rnd_xorshift_seed(&rnd, t);
	
	for(int i = 0; i < ucda->l; i += 2) {
		if( rnd_xorshift_next(&rnd) % 2 ) {
			ucda->a[i] = toupper(ucda->a[i]);
		} else {
			ucda->a[i] = tolower(ucda->a[i]);
		}
	}

	msd_ucda_printall(ucda);

	msd_ucda_free(ucda);
}
