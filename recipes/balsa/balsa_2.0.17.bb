DESCRIPTION = "Balsa is an e-mail client for GNOME, highly configurable and \
incorporating all the features you would expect in a robust mail client."
HOMEPAGE = "http://balsa.gnome.org"
SECTION = "x11/network"
LICENSE = "GPL"
DEPENDS = "libesmtp glib-2.0 libgnome libgnomeui gtk+ gnome-vfs libbonobo aspell libpcre libtool openssl gtkhtml-3.0"
RDEPENDS = "gdk-pixbuf-loader-xpm"
PR = "r3"

SRC_URI = "http://talinux.fi.tal.org/pub/talinux/sources/balsa-${PV}.tar.bz2 \
	   file://libmutt-cross.patch;patch=1 \
	   file://desktop-file-fix.patch;patch=1"

inherit autotools

EXTRA_OECONF = "--with-ssl"

do_configure_prepend() {
	# aclocal seems to insist on looking in here.  Make sure it exists.
	mkdir -p ${S}/m4
	# work around automake lossage with AC_CONFIG_AUX_DIR
	( cd libmutt; libtoolize --force ; cp ../ltmain.sh . )
}

