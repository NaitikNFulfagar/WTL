<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version ="1.0" xmlns:xsl ="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> <body>
<h2>employees</h2>
<table border = "1">
<tr bgcolor = "#9acd32">
<th>ID</th>
<th>First Name</th>
<th>Last Name</th>
<th>Designation</th>
</tr>
<xsl:for-each select="class/employee">
<tr>
<td><xsl:value-of select="@id"/></td>
<td><xsl:value-of select="firstname"/></td>
<td><xsl:value-of select="lastname"/></td>
<td><xsl:value-of select="designation"/></td>
</tr>
</xsl:for-each></table>
</body></html>
</xsl:template>
</xsl:stylesheet>