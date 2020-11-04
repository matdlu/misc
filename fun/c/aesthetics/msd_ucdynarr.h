#ifndef MSD_UCDYNNAR
#define MSD_UCDYNNAR

#include<stdio.h>
#include<stdlib.h>

size_t msd_UCDYNARR_LENGTH = 1024;

typedef struct Unsigned_Char_Dynamic_Array {
	size_t l;
	size_t c;
	unsigned char* a;
} ucdynarr;

ucdynarr* msd_ucda() {
	ucdynarr* da = malloc(sizeof(ucdynarr));

	da->l = 0;
	da->c = msd_UCDYNARR_LENGTH;
	da->a = malloc(msd_UCDYNARR_LENGTH);

	return da;
}

void msd_ucda_add(ucdynarr* ucda, unsigned char c) {
	if( ucda->l >= ucda->c ) {
		ucda->c *= 2;
		ucda->a = realloc(ucda->a, ucda->c);
	} else {
		ucda->a[ucda->l] = c;
	}

	ucda->l++;
}

char msd_ucda_get(ucdynarr* ucda, size_t i) {
	return ucda->a[i];
}

void msd_ucda_free(ucdynarr* ucda) {
	free(ucda->a);

	free(ucda);
}

void msd_ucda_printall(ucdynarr* ucda) {
	for(int i = 0; i < ucda->l; i++) {
		putchar(ucda->a[i]);
	}
	putchar('\n');
}

#endif /* MSD_UCDYNNAR */