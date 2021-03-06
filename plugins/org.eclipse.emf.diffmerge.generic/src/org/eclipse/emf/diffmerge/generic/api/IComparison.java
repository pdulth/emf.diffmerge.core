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
package org.eclipse.emf.diffmerge.generic.api;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.scopes.IDataScope;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;


/**
 * A comparison between data scopes.
 * A comparison, once defined, can be computed using match, diff and merge policies.
 * After computation, it provides a mapping between the contents of the scopes,
 * a set of differences based on this mapping and the ability to merge a subset of
 * those differences according to the merge policy.
 * If the match policy provided was not suitable to the scopes, then the computed
 * comparison is not consistent and merge is discouraged.
 * @see IDataScope
 * @see IMatchPolicy
 * @see IDiffPolicy
 * @see IMergePolicy
 * @see IDifference
 * @see IComparison#isConsistent()
 *
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public interface IComparison<E> {
  
  /**
   * Clear this comparison, going back to its state before compute(...) was called
   */
  void clear();
  
  /**
   * Compute this comparison according to the given policies
   * Postcondition: if result.isOk() then getLastMergePolicy() != null
   * @param matchPolicy_p an optional match policy (null stands for default)
   * @param diffPolicy_p an optional diff policy (null stands for default)
   * @param mergePolicy_p an optional merge policy (null stands for default)
   * @param monitor_p an optional progress monitor
   * @return a non-null status of the execution
   */
  IStatus compute(IMatchPolicy<E> matchPolicy_p, IDiffPolicy<E> diffPolicy_p,
      IMergePolicy<E> mergePolicy_p, IProgressMonitor monitor_p);
  
  /**
   * Return a tree iterator over matches based on getContentsOf(IMatch, Role)
   * @see IComparison#getContentsOf(IMatch, Role)
   * @param role_p a non-null role
   * @return a non-null iterator
   */
  TreeIterator<IMatch<E>> getAllContents(Role role_p);
  
  /**
   * Return the match for the container of the given match in the given role.
   * Result is null if and only if
   * - there is no container in the corresponding scope
   * - or there is no element in the role for the given match
   * - or there is such an element but it has been removed from the scope through a
   *    merge operation.
   * @param match_p a non-null match
   * @param role_p a non-null role
   * @return a potentially null match
   */
  IMatch<E> getContainerOf(IMatch<E> match_p, Role role_p);
  
  /**
   * Return the matches for the roots of the TARGET and REFERENCE scopes.
   * The result is based on the current contents of the scope, i.e., it excludes
   * matches of elements removed from the scope through a merge operation.
   * @return a non-null, potentially empty, unmodifiable ordered set of matches
   */
  List<IMatch<E>> getContents();
  
  /**
   * Return the matches for the roots of the scope of the given role.
   * The result is based on the current contents of the scope, i.e., it excludes
   * matches of elements removed from the scope through a merge operation.
   * @param role_p a non-null role
   * @return a non-null, potentially empty, unmodifiable ordered set of matches
   */
  List<IMatch<E>> getContents(Role role_p);
  
  /**
   * Return the matches for the contents of the given match in the TARGET and
   * REFERENCE roles. Matches from REFERENCE come first.
   * The result is based on the current contents of the scope, i.e., it excludes
   * matches of elements removed from the scope through a merge operation.
   * @param match_p a non-null match
   * @return a non-null, potentially empty, unmodifiable ordered set of matches
   */
  List<IMatch<E>> getContentsOf(IMatch<E> match_p);
  
  /**
   * Return the matches for the contents of the given match in the given role.
   * The result is based on the current contents of the scope, i.e., it excludes
   * matches of elements removed from the scope through a merge operation.
   * @param match_p a non-null match
   * @param role_p a non-null role
   * @return a non-null, potentially empty, unmodifiable ordered set of matches
   */
  List<IMatch<E>> getContentsOf(IMatch<E> match_p, Role role_p);
  
  /**
   * Return all differences that are due to a presence of data in the given role.
   * This operation cannot be assumed to be efficient.
   * The resulting collection may become obsolete if the comparison is reset.
   * @param role_p a role which is TARGET or REFERENCE
   * @return a non-null, unmodifiable list which may contain duplicates if differences
   *         are not low-level, technical differences
   */
  Collection<IDifference<E>> getDifferences(Role role_p);
  
  /**
   * Return the set of duplicate match IDs for the given role, if any.
   * If the result is not empty, then it means that the match policy that
   * was used is not applicable to the scope of the given role.
   * @see IComparison#isConsistent()
   * @param role_p a non-null role
   * @return a non-null, possibly empty collection
   */
  Collection<Object> getDuplicateMatchIDs(Role role_p);
  
  /**
   * Return the last diff policy used by this comparison
   * @return a possibly null diff policy (non-null if the last compute(...) succeeded)
   */
  IDiffPolicy<E> getLastDiffPolicy();
  
  /**
   * Return the last match policy used by this comparison
   * @return a possibly null match policy (non-null if the last compute(...) succeeded)
   */
  IMatchPolicy<E> getLastMatchPolicy();
  
  /**
   * Return the last merge policy used by this comparison
   * @return a possibly null merge policy (non-null if the last compute(...) succeeded)
   */
  IMergePolicy<E> getLastMergePolicy();
  
  /**
   * Return the mapping between the model scopes of this comparison
   * @return a non-null mapping
   */
  IMapping<E> getMapping();
  
  /**
   * Return the number of differences as the sum of the number of differences
   * per mapping entry
   */
  int getNbDifferences();
  
  /**
   * Return the number of differences which are not related to the containment
   * tree, i.e., those on attributes and cross references
   * Class invariant: getNbNoContainmentDifferences() <= getNbDifferences()
   */
  int getNbNoContainmentDifferences();
  
  /**
   * Return the set of differences which have not been merged.
   * It is a subset of the union of getDifferences(TARGET) and getDifferences(REFERENCE).
   * The resulting collection may become obsolete if the comparison is reset.
   * @return a non-null, potentially empty, unmodifiable collection
   */
  Collection<IDifference<E>> getRemainingDifferences();
  
  /**
   * Return the scope for the given role
   * @param role_p a non-null role
   * @return a scope which is non-null iff the given role is covered by this comparison
   */
  ITreeDataScope<E> getScope(Role role_p);
  
  /**
   * Return whether there are differences which have not been merged.
   * Class invariant: hasRemainingDifferences() == !getRemainingDifferences().isEmpty()
   * @return a non-null, potentially empty, unmodifiable collection
   */
  boolean hasRemainingDifferences();
  
  /**
   * Return whether this comparison was consistently computed, i.e., no duplicate
   * match ID was encountered during computation.
   * If false, then consistency of merge operations is not guaranteed.
   * @see IComparison#getDuplicateMatchIDs(Role)
   */
  boolean isConsistent();
  
  /**
   * Return whether this comparison is three-way and not two-way, that is,
   * whether it has an ancestor scope which allows detecting conflicts
   * between the target and reference scopes
   */
  boolean isThreeWay();
  
  /**
   * Merge the remaining differences to the given destination role
   * @param destination_p a role which is TARGET or REFERENCE
   * @param updateReferences_p whether references of the added elements must be set
   * @param monitor_p an optional progress monitor (null for no progress monitoring)
   * @return a non-null, potentially empty, unmodifiable set of the differences
   *         which have actually been merged
   */
  Collection<IDifference<E>> merge(Role destination_p,
      boolean updateReferences_p, IProgressMonitor monitor_p);
  
  /**
   * Merge the given set of differences to the given destination role
   * @param differences_p a non-null, potentially empty set of differences
   * @param destination_p a role which is TARGET or REFERENCE
   * @param updateReferences_p whether references of the added elements must be set
   * @param monitor_p an optional progress monitor (null for no progress monitoring)
   * @return a non-null, potentially empty, unmodifiable set of the differences
   *         which have actually been merged
   */
  Collection<IDifference<E>> merge(
      Collection<? extends IDifference<E>> differences_p, Role destination_p,
      boolean updateReferences_p, IProgressMonitor monitor_p);
  
  /**
   * Apply the given merge selector to the comparison
   * @see IMergeSelector
   * @param merger_p a non-null merger
   * @param updateReferences_p whether references of the added elements must be set
   * @param monitor_p an optional progress monitor (null for no progress monitoring)
   * @return a non-null, potentially empty, unmodifiable set of the differences
   *         which have actually been merged
   */
  Collection<IDifference<E>> merge(IMergeSelector<E> merger_p,
      boolean updateReferences_p, IProgressMonitor monitor_p);
  
  
  /**
   * A comparison with editing features.
   * All concrete classes implementing IComparison must also implement this interface.
   */
  interface Editable<E> extends IComparison<E> {
    /**
     * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getMapping()
     */
    IMapping.Editable<E> getMapping();
    
    /**
     * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getScope(org.eclipse.emf.diffmerge.generic.api.Role)
     */
    IEditableTreeDataScope<E> getScope(Role role_p);
    
    /**
     * Create and return an attribute value presence with the given characteristics
     * @param elementMatch_p the non-null match for the element holding the value
     * @param attribute_p the non-null attribute holding the value
     * @param value_p the non-null value held
     * @param presenceRole_p the non-null role in which the value is held: TARGET or REFERENCE
     * @param isOrder_p whether the value presence is solely due to ordering
     * @return a non-null attribute value presence
     */
    IAttributeValuePresence<E> newAttributeValuePresence(
        IMatch<E> elementMatch_p, Object attribute_p, Object value_p,
        Role presenceRole_p, boolean isOrder_p);
    
    /**
     * Create and return an element presence with the given characteristics
     * @param elementMatch_p the non-null partial match for the element presence
     * @param ownerMatch_p a potentially null match for the owner of the element
     * @param presenceRole_p the non-null role in which the element is present: TARGET or REFERENCE
     * @return a non-null element presence
     */
    IElementPresence<E> newElementPresence(IMatch<E> elementMatch_p,
        IMatch<E> ownerMatch_p, Role presenceRole_p);
    
    /**
     * Create and return a match with the given characteristics
     * @param targetElement_p an optional element on the TARGET side
     * @param referenceElement_p an optional element on the REFERENCE side
     * @param ancestorElement_p an optional element on the ANCESTOR side
     * @return a non-null match
     */
    IMatch<E> newMatch(E targetElement_p, E referenceElement_p,
        E ancestorElement_p);
    
    /**
     * Create and return a reference value presence with the given characteristics
     * @param elementMatch_p the non-null match for the element holding the value
     * @param reference_p the non-null reference holding the value
     * @param value_p the value element, which may only be null if valueMatch_p is not null
     * @param valueMatch_p an optional match, which cannot be null if value_p or reference_p is null
     * @param presenceRole_p the non-null role in which the value is held: TARGET or REFERENCE
     * @param isOrder_p whether the value presence is solely due to ordering
     * @return a non-null reference value presence
     */
    IReferenceValuePresence<E> newReferenceValuePresence(
        IMatch<E> elementMatch_p, Object reference_p, E value_p,
        IMatch<E> valueMatch_p, Role presenceRole_p, boolean isOrder_p);
    
    /**
     * Swap the TARGET and REFERENCE scopes.
     * This operation succeeds if and only if the comparison is empty:
     * it has not been computed yet or it has been cleared.
     * @return whether the operation succeeded
     */
    boolean swapScopes();
  }
  
}
