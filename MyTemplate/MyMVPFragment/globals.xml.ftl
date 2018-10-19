<?xml version="1.0"?>
<globals>

    <global id="superClassName" type="string" value="
            <#if superClass == 'baseFragment'>BaseFragment</#if>
        " />

    <global id="superClassFqcn" type="string" value="
            <#if superClass == 'baseFragment'>${pathContainComponent}.ui.base.BaseFragment</#if>
        " />

    <global id="manifestOut" value="${manifestDir}" />
    <global id="useSupport" type="boolean" value="${(minApiLevel lt 11)?string}" />
    <global id="resOut" value="${resDir}" />
    <global id="srcOut" value="${srcDir}/${slashedPackageName(pathContainComponent)}" />
    <global id="relativePackage" value=".ui" />
    <global id="subpackage" value="<#if useSubPackage>${subPackage}/<#else></#if>" />
    <global id="dotSubpackage" value="<#if useSubPackage>.${subPackage}<#else></#if>" />
	<#if useAdapter>
		<global id="modelsubpackage" value="<#if useAdapter>${modelsubPackage}/<#else></#if>" />
		<global id="dotModelSubpackage" value="<#if useAdapter>.${modelsubPackage}<#else></#if>" />
    </#if>
</globals>
