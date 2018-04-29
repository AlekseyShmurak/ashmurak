<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <xsl:text>&#10;</xsl:text>
        <entries><xsl:text>&#10;</xsl:text>
            <xsl:for-each select="entries/entry">
                <entry>
                    <xsl:attribute name="field"><xsl:value-of select="field"/></xsl:attribute>
                </entry><xsl:text>&#10;</xsl:text>
            </xsl:for-each>
        </entries>
    </xsl:template>

</xsl:stylesheet>