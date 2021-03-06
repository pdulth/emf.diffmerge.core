/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.generic.gdiffdata;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.IDataPolicy;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.IMapping.Editable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>GMapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMapping#getModifiableContents <em>Modifiable Contents</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMapping#getReferenceCompletedMatches <em>Reference Completed Matches</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMapping#getTargetCompletedMatches <em>Target Completed Matches</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getGMapping()
 * @model abstract="true" superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.GIdentified org.eclipse.emf.diffmerge.generic.gdiffdata.GComparisonElement&lt;E, A, R&gt; org.eclipse.emf.diffmerge.generic.gdiffdata.IEditableMapping&lt;E&gt;"
 * @generated
 */
public interface GMapping<E, A, R>
    extends GIdentified, GComparisonElement<E, A, R>, Editable<E> {
  /**
   * Returns the value of the '<em><b>Modifiable Contents</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch}<code>&lt;E, A, R&gt;</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modifiable Contents</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modifiable Contents</em>' containment reference list.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getGMapping_ModifiableContents()
   * @model containment="true"
   * @generated
   */
  EList<GMatch<E, A, R>> getModifiableContents();

  /**
   * Returns the value of the '<em><b>Reference Completed Matches</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.generic.api.IMatch}<code>&lt;E&gt;</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reference Completed Matches</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference Completed Matches</em>' reference list.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getGMapping_ReferenceCompletedMatches()
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IMatch&lt;E&gt;" resolveProxies="false"
   * @generated
   */
  EList<IMatch<E>> getReferenceCompletedMatches();

  /**
   * Returns the value of the '<em><b>Target Completed Matches</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.generic.api.IMatch}<code>&lt;E&gt;</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Completed Matches</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Completed Matches</em>' reference list.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getGMapping_TargetCompletedMatches()
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IMatch&lt;E&gt;" resolveProxies="false"
   * @generated
   */
  EList<IMatch<E>> getTargetCompletedMatches();

  /**
   * Remove dependencies (reference values) to the given element so that its removal
   * from the scope of the given role be possible, if required by the scope.
   * Dependencies covered by the comparison through reference value presences do not
   * need to be taken into account. Dependencies that must be taken into account are
   * those whose removal is necessary to the removal of the element from its scope
   * AND that are not covered by the comparison, i.e.,
   * - references from other elements of the scope that, like the element itself, are
   *    not present in the opposite scope;
   * - references from other elements of the scope that are ignored due to the diff
   *    policy;
   * - references from elements outside the scope.
   * @param role TARGET or REFERENCE
   * @param element a non-null element
   * @return whether all dependencies have been successfully removed or the scope does
   *          not require it
   * @see IDataPolicy#tIsElementDisconnectionRequired()
   * @generated NOT
   */
  boolean disconnect(Role role, E element);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model required="true" sourceRequired="true" referenceRequired="true" valueRequired="true" roleDataType="org.eclipse.emf.diffmerge.generic.gdiffdata.Role" roleRequired="true"
   * @generated
   */
  boolean isIgnoredReferenceValue(E source, R reference, E value, Role role);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model required="true" elementRequired="true" roleDataType="org.eclipse.emf.diffmerge.generic.gdiffdata.Role" roleRequired="true"
   * @generated
   */
  GMatch<E, A, R> map(E element, Role role);

} // GMapping
