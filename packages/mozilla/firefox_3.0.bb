DEPENDS += "cairo"
PR = "r0"

SRC_URI = "http://ftp.mozilla.org/pub/mozilla.org/firefox/releases/3.0/source/firefox-${PV}-source.tar.bz2 \
	file://jsautocfg.h \
	file://security-cross.patch;patch=1 \
	file://jsautocfg-dontoverwrite.patch;patch=1 \
	file://Bug339782.additional.fix.diff;patch=1 \
	file://Bug385583.nspr.jmp_buf.eabi.diff;patch=1 \
	file://Bug405992.atomic.nspr.diff;patch=1 \
	file://random_to_urandom.diff;patch=1 \
	file://jemalloc-tls.patch;patch=1 \
	http://ftp.mozilla.org/pub/mozilla.org/js/js-1.7.0.tar.gz \
"

S = "${WORKDIR}/mozilla"

#DEFAULT_PREFERENCE = "-1"

inherit mozilla
require firefox.inc

do_unpack2() {
	cp -pPr ${WORKDIR}/js/src* ${S}/js/
}

do_compile_prepend() {
	cp ${WORKDIR}/jsautocfg.h ${S}/js/src/
	sed -i "s|CPU_ARCH =|CPU_ARCH = ${TARGET_ARCH}|" security/coreconf/Linux.mk
}

do_stage() {
        install -d ${STAGING_INCDIR}/firefox-${PV}
        cd dist/sdk/include
		rm -rf obsolete
        headers=`find . -name "*.h"`
        for f in $headers
        do
                install -D -m 0644 $f ${STAGING_INCDIR}/firefox-${PV}/
        done
        # removes 2 lines that call absent headers
        sed -e '178,179d' ${STAGING_INCDIR}/firefox-${PV}/nsIServiceManager.h
}

addtask unpack2 after do_unpack before do_patch