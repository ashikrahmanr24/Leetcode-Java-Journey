class Solution(object):
    def removeElement(self, nums, val):
        while val in nums:
            nums.remove(val)