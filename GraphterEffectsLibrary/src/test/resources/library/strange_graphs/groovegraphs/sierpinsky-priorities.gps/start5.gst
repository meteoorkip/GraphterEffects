<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<gxl xmlns="http://www.gupro.de/GXL/gxl-1.0.dtd">
    <graph edgemode="directed" edgeids="false" role="graph" id="start5">
        <node id="n3">
            <attr name="layout">
                <string>155 29 8 16</string>
            </attr>
        </node>
        <node id="n0">
            <attr name="layout">
                <string>196 171 15 15</string>
            </attr>
        </node>
        <node id="n4">
            <attr name="layout">
                <string>498 29 8 16</string>
            </attr>
        </node>
        <node id="n6">
            <attr name="layout">
                <string>425 30 8 16</string>
            </attr>
        </node>
        <node id="n8">
            <attr name="layout">
                <string>111 174 15 15</string>
            </attr>
        </node>
        <node id="n1">
            <attr name="layout">
                <string>225 20 45 32</string>
            </attr>
        </node>
        <node id="n2">
            <attr name="layout">
                <string>571 29 8 16</string>
            </attr>
        </node>
        <node id="n5">
            <attr name="layout">
                <string>342 30 8 16</string>
            </attr>
        </node>
        <node id="n7">
            <attr name="layout">
                <string>155 99 15 15</string>
            </attr>
        </node>
        <edge to="n7" from="n0">
            <attr name="label">
                <string>v</string>
            </attr>
        </edge>
        <edge to="n1" from="n1">
            <attr name="label">
                <string>flag:current</string>
            </attr>
        </edge>
        <edge to="n3" from="n7">
            <attr name="label">
                <string>belongs</string>
            </attr>
        </edge>
        <edge to="n7" from="n8">
            <attr name="label">
                <string>v</string>
            </attr>
        </edge>
        <edge to="n2" from="n2">
            <attr name="label">
                <string>5</string>
            </attr>
        </edge>
        <edge to="n4" from="n6">
            <attr name="label">
                <string>next</string>
            </attr>
        </edge>
        <edge to="n4" from="n4">
            <attr name="label">
                <string>4</string>
            </attr>
        </edge>
        <edge to="n5" from="n5">
            <attr name="label">
                <string>2</string>
            </attr>
        </edge>
        <edge to="n1" from="n1">
            <attr name="label">
                <string>1</string>
            </attr>
        </edge>
        <edge to="n3" from="n3">
            <attr name="label">
                <string>0</string>
            </attr>
        </edge>
        <edge to="n6" from="n5">
            <attr name="label">
                <string>next</string>
            </attr>
        </edge>
        <edge to="n6" from="n6">
            <attr name="label">
                <string>3</string>
            </attr>
        </edge>
        <edge to="n1" from="n3">
            <attr name="label">
                <string>next</string>
            </attr>
        </edge>
        <edge to="n0" from="n8">
            <attr name="label">
                <string>h</string>
            </attr>
        </edge>
        <edge to="n5" from="n1">
            <attr name="label">
                <string>next</string>
            </attr>
        </edge>
        <edge to="n2" from="n4">
            <attr name="label">
                <string>next</string>
            </attr>
        </edge>
    </graph>
</gxl>
