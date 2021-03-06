DESCRIPTION = "Web server"
SECTION = "net"
LICENSE = "BSD"
DEPENDS = "libpcre"
PR = "r3"

SRC_URI = "http://www.lighttpd.net/download/lighttpd-${PV}.tar.gz \
	   file://configure.in.patch;patch=1 \
	   file://mod_redirect.c.patch;patch=1 \
	   file://src-server.c.patch;patch=1 \
	   file://index.html \
	   file://lighttpd.conf \
	   file://lighttpd"

EXTRA_OECONF="--without-bzip2 \
		--without-ldap \
		--without-lua \
		--without-memcache \
		--with-pcre \
		--without-webdav-props \
		--without-webdav-locks \
		--without-openssl \
		--disable-nls \
		--disable-static"

inherit autotools pkgconfig update-rc.d

INITSCRIPT_NAME = "lighttpd"
INITSCRIPT_PARAMS = "defaults 70"

do_install_append() {
	install -d ${D}${sysconfdir}/init.d ${D}/www/logs ${D}/www/pages/dav ${D}/www/var
	install -m 0755 ${WORKDIR}/lighttpd ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/lighttpd.conf ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/index.html ${D}/www/pages/
}

do_stage() {
	autotools_stage_all
}

FILES_${PN} += "${sysconfdir} /www"

PACKAGES_DYNAMIC = "lighttpd-module-*"

python populate_packages_prepend () {
        lighttpd_libdir = bb.data.expand('${libdir}', d)
        do_split_packages(d, lighttpd_libdir, '^mod_(.*)\.so$', 'lighttpd-module-%s', 'Lighttpd module for %s', extra_depends='')
}
