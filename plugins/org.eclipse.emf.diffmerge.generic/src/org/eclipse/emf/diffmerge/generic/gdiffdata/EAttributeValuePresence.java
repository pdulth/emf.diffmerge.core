/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
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

import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EAttribute Value Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEAttributeValuePresence()
 * @model abstract="true" superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.EValuePresence&lt;E, A, R&gt; org.eclipse.emf.diffmerge.generic.gdiffdata.IAttributeValuePresence&lt;E&gt;"
 * @generated
 */
public interface EAttributeValuePresence<E, A, R>
    extends EValuePresence<E, A, R>, IAttributeValuePresence<E> {

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model valueRequired="true"
   * @generated
   */
  void setValue(Object value);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation" required="true"
   * @generated
   */
  A getFeature();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model attributeRequired="true"
   * @generated
   */
  void setAttribute(A attribute);
  // Nothing needed
} // EAttributeValuePresence