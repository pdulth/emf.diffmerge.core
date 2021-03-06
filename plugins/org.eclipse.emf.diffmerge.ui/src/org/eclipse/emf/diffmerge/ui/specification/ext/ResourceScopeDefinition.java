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
package org.eclipse.emf.diffmerge.ui.specification.ext;

import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope;
import org.eclipse.emf.diffmerge.ui.specification.AbstractScopeDefinition;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * A scope definition based on a resource within a resource set.
 * @author Olivier Constant
 */
public class ResourceScopeDefinition extends AbstractScopeDefinition {
  
  /**
   * Constructor
   * @param resource_p a non-null resource which belongs to a non-null resource set
   * @param label_p an optional label
   * @param editable_p whether the scope can be edited
   */
  public ResourceScopeDefinition(Resource resource_p, String label_p, boolean editable_p) {
    super(resource_p,
        label_p != null? label_p: getLabelForResource(resource_p), editable_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#createScope(java.lang.Object)
   */
  public IEditableTreeDataScope<?> createScope(Object context_p) {
    return new FragmentedModelScope(getEntrypoint(), !isEditable());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.AbstractScopeDefinition#getEntrypoint()
   */
  @Override
  public Resource getEntrypoint() {
    return (Resource)super.getEntrypoint();
  }
  
  /**
   * Return a label for the given resource
   * @param resource_p a non-null resource
   * @return a non-null string
   */
  protected static String getLabelForResource(Resource resource_p) {
    String result;
    if (resource_p.getURI() != null)
      result = UIUtil.simplifyURI(resource_p.getURI());
    else
      result = resource_p.toString();
    return result;
  }
  
}
