class AddstationIdColumnToCustomer < ActiveRecord::Migration
  def change
    add_column :reservations, :station_id, :integer
  end
end
