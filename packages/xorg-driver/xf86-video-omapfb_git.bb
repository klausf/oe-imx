require xorg-driver-video.inc

DESCRIPTION = "X.Org X server -- OMAP display driver"

PR ="r4"

SRCREV = "fc5958ba70ad76aadcf2993d7713ee13fe262b33"
PV = "0.0.1+${PR}+git${SRCREV}"

SRC_URI = "git://git.pingu.fi/xf86-video-omapfb.git;protocol=http"

S = "${WORKDIR}/git"

CFLAGS += " -I${STAGING_INCDIR}/xorg "