<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<gxl xmlns="http://www.gupro.de/GXL/gxl-1.0.dtd">
    <graph edgemode="directed" edgeids="false" role="graph" id="start1">
        <attr name="$version">
            <string>curly</string>
        </attr>
        <node id="n11">
            <attr name="layout">
                <string>34 166 56 31</string>
            </attr>
        </node>
        <node id="n7">
            <attr name="layout">
                <string>101 114 51 31</string>
            </attr>
        </node>
        <node id="n5">
            <attr name="layout">
                <string>280 40 81 31</string>
            </attr>
        </node>
        <node id="n1">
            <attr name="layout">
                <string>378 424 51 31</string>
            </attr>
        </node>
        <node id="n12">
            <attr name="layout">
                <string>82 361 81 31</string>
            </attr>
        </node>
        <node id="n4">
            <attr name="layout">
                <string>349 171 56 31</string>
            </attr>
        </node>
        <node id="n3">
            <attr name="layout">
                <string>222 265 51 31</string>
            </attr>
        </node>
        <node id="n0">
            <attr name="layout">
                <string>74 441 51 31</string>
            </attr>
        </node>
        <node id="n13">
            <attr name="layout">
                <string>169 200 81 31</string>
            </attr>
        </node>
        <node id="n2">
            <attr name="layout">
                <string>287 110 51 31</string>
            </attr>
        </node>
        <node id="n10">
            <attr name="layout">
                <string>346 254 57 31</string>
            </attr>
        </node>
        <node id="n8">
            <attr name="layout">
                <string>288 364 81 31</string>
            </attr>
        </node>
        <node id="n9">
            <attr name="layout">
                <string>114 270 51 31</string>
            </attr>
        </node>
        <node id="n6">
            <attr name="layout">
                <string>94 33 81 31</string>
            </attr>
        </node>
        <edge to="n11" from="n11">
            <attr name="label">
                <string>type:Token</string>
            </attr>
        </edge>
        <edge to="n13" from="n7">
            <attr name="label">
                <string>in</string>
            </attr>
        </edge>
        <edge to="n7" from="n7">
            <attr name="label">
                <string>type:Place</string>
            </attr>
        </edge>
        <edge to="n11" from="n7">
            <attr name="label">
                <string>mark</string>
            </attr>
            <attr name="layout">
                <string>714 8 135 134 72 186 11</string>
            </attr>
        </edge>
        <edge to="n5" from="n5">
            <attr name="label">
                <string>type:Transition</string>
            </attr>
        </edge>
        <edge to="n2" from="n5">
            <attr name="label">
                <string>out</string>
            </attr>
        </edge>
        <edge to="n1" from="n1">
            <attr name="label">
                <string>type:Place</string>
            </attr>
        </edge>
        <edge to="n5" from="n1">
            <attr name="label">
                <string>in</string>
            </attr>
            <attr name="layout">
                <string>500 0 428 433 514 374 514 102 369 68 11</string>
            </attr>
        </edge>
        <edge to="n12" from="n12">
            <attr name="label">
                <string>type:Transition</string>
            </attr>
        </edge>
        <edge to="n0" from="n12">
            <attr name="label">
                <string>out</string>
            </attr>
        </edge>
        <edge to="n4" from="n4">
            <attr name="label">
                <string>type:Token</string>
            </attr>
        </edge>
        <edge to="n10" from="n3">
            <attr name="label">
                <string>mark</string>
            </attr>
        </edge>
        <edge to="n3" from="n3">
            <attr name="label">
                <string>type:Place</string>
            </attr>
        </edge>
        <edge to="n8" from="n3">
            <attr name="label">
                <string>in</string>
            </attr>
        </edge>
        <edge to="n6" from="n0">
            <attr name="label">
                <string>in</string>
            </attr>
            <attr name="layout">
                <string>500 0 95 450 14 384 14 126 127 64 11</string>
            </attr>
        </edge>
        <edge to="n0" from="n0">
            <attr name="label">
                <string>type:Place</string>
            </attr>
        </edge>
        <edge to="n13" from="n13">
            <attr name="label">
                <string>type:Transition</string>
            </attr>
        </edge>
        <edge to="n3" from="n13">
            <attr name="label">
                <string>out</string>
            </attr>
        </edge>
        <edge to="n9" from="n13">
            <attr name="label">
                <string>out</string>
            </attr>
        </edge>
        <edge to="n2" from="n2">
            <attr name="label">
                <string>type:Place</string>
            </attr>
        </edge>
        <edge to="n4" from="n2">
            <attr name="label">
                <string>mark</string>
            </attr>
        </edge>
        <edge to="n13" from="n2">
            <attr name="label">
                <string>in</string>
            </attr>
        </edge>
        <edge to="n10" from="n10">
            <attr name="label">
                <string>type:Token</string>
            </attr>
        </edge>
        <edge to="n1" from="n8">
            <attr name="label">
                <string>out</string>
            </attr>
        </edge>
        <edge to="n8" from="n8">
            <attr name="label">
                <string>type:Transition</string>
            </attr>
        </edge>
        <edge to="n12" from="n9">
            <attr name="label">
                <string>in</string>
            </attr>
        </edge>
        <edge to="n9" from="n9">
            <attr name="label">
                <string>type:Place</string>
            </attr>
        </edge>
        <edge to="n7" from="n6">
            <attr name="label">
                <string>out</string>
            </attr>
        </edge>
        <edge to="n6" from="n6">
            <attr name="label">
                <string>type:Transition</string>
            </attr>
        </edge>
    </graph>
</gxl>
